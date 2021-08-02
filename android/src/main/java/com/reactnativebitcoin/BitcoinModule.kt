package com.reactnativebitcoin

import com.facebook.react.bridge.*
import fr.acinq.bitcoin.*
import fr.acinq.bitcoin.MnemonicCode.toMnemonics
import java.lang.Exception
import kotlin.random.Random
import fr.acinq.bitcoin.Bitcoin.computeBIP84Address

val PATH = "m/84'/0'/0'/0/0"

class BitcoinModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return "Bitcoin"
  }

  @ReactMethod
  fun generateMnemonic(length: Int = 16, promise: Promise) {
    val random = Random
    val entropy = ByteArray(length)
    random.nextBytes(entropy)
    val mnemonicsList = toMnemonics(entropy)
    val mnemonics = mnemonicsList.joinToString(" ")
    val wallet = _mnemonicToWallet(mnemonics, PATH)
    wallet.apply {
      putString("mnemonics", mnemonics)
    }

    promise.resolve(wallet)
  }


  @ReactMethod
  fun mnemonicToWallet(mnemonic: String, promise: Promise) {
    val wallet = _mnemonicToWallet(mnemonic, PATH)
    promise.resolve(wallet)
  }

  fun _mnemonicToWallet(mnemonic: String, path: String): WritableMap {
    val seed = MnemonicCode.toSeed(mnemonic, "")
    val master = DeterministicWallet.generate(seed)
    val path = KeyPath(path)
    val account = DeterministicWallet.derivePrivateKey(master, path)
    val xprv = DeterministicWallet.encode(master, testnet = false)
    val address = computeBIP84Address(account.publicKey, Block.LivenetGenesisBlock.hash)
    return Arguments.createMap().apply {
      putString("address", address)
      putString("publicKey", account.publicKey.toString())
      putString("privateKey", account.privateKey.toBase58(Base58.Prefix.SecretKey))
      putString("root32xprv", xprv)
    }
  }


  fun getPath(path: String): Int {
    if (path.contains("m/44")) return DeterministicWallet.tpub
    if (path.contains("m/49")) return DeterministicWallet.upub
    if (path.contains("m/84")) return DeterministicWallet.zpub
    else throw Exception("Invalid path")
  }
}
