# rn-btc-fast-sign-message

bitcoin sign utils 

small lib to make sign message and access wallet from mnemonic based on [bitcoin-kmp](https://github.com/ACINQ/bitcoin-kmp)

## Installation

```sh
npm install react-native-bitcoin
```

## Usage

```js
import Bitcoin from "react-native-bitcoin";

interface WalletInfo {
  address: string,
  mnemonics: string,
  privateKey: string,
  publicKey: string,
  root32xprv: string
}

// ...

const wallet: WalletInfo = await Bitcoin.generateMnemonic(16) // <- lengt of randombytes

// or
const mnemonics = "whip position jazz switch where cupboard leader slice nephew unusual hand maple"
const wallet: WalletInfo = await Bitcoin.mnemonicToWallet()

//  address: "bc1q2cxk23lns53p6y5f7ht2kf04r7myr06w0uh3su", <- address as bip84
//  mnemonics: "whip position jazz switch where cupboard leader slice nephew unusual hand maple", <- mnemonic when generate
//  privateKey: "L35esJKrpKByKh7CiDUaD1bkCbUi65SWXY4xfYJNWLFMvhSzXKJX", <- privatekey as bip84
//  publicKey: "0295ba81e1ed4c09441b1ea62cb9d25318ecad29b6e3d425dfb5d071a0a948da29", <- publickey as bip84
//  root32xprv: "xprv9s21ZrQH143K27UcMoKcw3CJMjaztFWmqkUHh4igNcZBNkvTQy5dYgmUkXxfrDMGb4p8QdbcNHKHQeHuMwA8bvM6BvKR56hYY6JPTncdFQK" <- root bip32 xprv

```

you can use with [bitcoinjs-message](https://github.com/bitcoinjs/bitcoinjs-message) it will look like

```js
  const msg = "this is a simple message"
  const child = bitcoin.bip32.fromBase58(wallet.root32xprv, bitcoin.networks.bitcoin).derivePath("m/84'/0'/0'/0/0")
  const signature = bitcoinMessage
      .sign(message, child.privateKey, true, {segwitType: 'p2wpkh'})
      .toString('base64')
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
