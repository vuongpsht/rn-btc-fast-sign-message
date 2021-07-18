package com.reactnativebitcoin

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import fr.acinq.bitcoin.DeterministicWallet
import fr.acinq.bitcoin.KeyPath
import fr.acinq.bitcoin.MnemonicCode
import fr.acinq.bitcoin.MnemonicCode.toMnemonics
import java.lang.Exception
import kotlin.random.Random
import fr.acinq.bitcoin.Bitcoin.computeBIP44Address
import fr.acinq.bitcoin.Bitcoin.computeBIP49Address
import fr.acinq.bitcoin.Bitcoin.computeBIP84Address
import fr.acinq.bitcoin.Block

class BitcoinModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String {
        return "Bitcoin"
    }

    // Example method
    // See https://reactnative.dev/docs/native-modules-android
    @ReactMethod
    fun multiply(a: Int, b: Int, promise: Promise) {

      promise.resolve(a * b)

    }

    @ReactMethod
    fun signMessage(privateKey: String, message: String, compressed: Boolean, messagePrefix: String, signOptions: String) {
      val prk = privateKey
    }
    @ReactMethod
    fun generateMnemonic(length: Int = 16,promise: Promise){
      val random = Random
      val entropy = ByteArray(length)
      random.nextBytes(entropy)
      val mnemonicsList = toMnemonics(entropy)
      val mnemonics = mnemonicsList.joinToString(" ")
      println(mnemonics)
      promise.resolve(mnemonics)
    }

    @ReactMethod
    fun mnemonicToWallet(mnemonic: String, path: String, promise: Promise){
      val pub = getPath(path)

      println("call to mnemonicToWallet, ${pub}")
      val seed = MnemonicCode.toSeed(mnemonic, "")
      val master = DeterministicWallet.generate(seed)
      val account = DeterministicWallet.derivePrivateKey(master, KeyPath(path))
      val xpub = DeterministicWallet.encode(DeterministicWallet.publicKey(account), pub)
      val (prefix) = DeterministicWallet.ExtendedPublicKey.decode(xpub)
      print("Block.LivenetGenesisBlock.hash, ${Block.LivenetGenesisBlock.hash}")
      val address = computeBIP44Address(account.publicKey, Block.LivenetGenesisBlock.hash)

      println("account.publicKey is ${account.publicKey}")
      println("address is ${address}")
      println("account prefix is ${prefix}")
      print("account is")
      print(account)
      promise.resolve("ahihi")
    }
    fun getPath(path: String):Int {
      if (path.contains("m/44")) return DeterministicWallet.tpub
      if (path.contains("m/49")) return DeterministicWallet.upub
      if (path.contains("m/84")) return DeterministicWallet.vpub
      else throw Exception("Invalid path")
    }
}
