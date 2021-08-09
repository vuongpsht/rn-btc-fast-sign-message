package com.reactnativebitcoin

import com.facebook.react.bridge.*
import io.github.novacrypto.bip32.ExtendedPrivateKey
import io.github.novacrypto.bip32.networks.Bitcoin
import io.github.novacrypto.bip39.MnemonicGenerator
import io.github.novacrypto.bip39.SeedCalculator
import io.github.novacrypto.bip39.Words
import io.github.novacrypto.bip39.wordlists.English
import java.lang.StringBuilder
import java.security.SecureRandom

val PATH = "m/0'/0'"

class BitcoinModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return "Bitcoin"
  }

  @ReactMethod
  fun generateMnemonic(length: Int = 16, promise: Promise) {
    val sb = StringBuilder()
    val entropy = ByteArray(Words.TWELVE.byteLength())
    val sr = SecureRandom()
    sr.nextBytes(entropy)
    MnemonicGenerator(English.INSTANCE)
      .createMnemonic(entropy, { sb.append(it) })
    val wallet = _mnemonicToWallet(sb.toString(), PATH)
    promise.resolve(wallet)
  }


  @ReactMethod
  fun mnemonicToWallet(mnemonic: String, promise: Promise) {
    val wallet = _mnemonicToWallet(mnemonic, PATH)
    promise.resolve(wallet)
  }

  fun _mnemonicToWallet(mnemonic: String, path: String): WritableMap {
    val seed =  SeedCalculator().calculateSeed(mnemonic, "");
    val key = ExtendedPrivateKey.fromSeed(seed, Bitcoin.MAIN_NET)
    val xprv = key.extendedBase58()
    return Arguments.createMap().apply {
      putString("root32xprv", xprv)
      putString("mnemonics", mnemonic)
    }
  }
}
