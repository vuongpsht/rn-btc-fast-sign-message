import { NativeModules } from 'react-native';

interface WalletInfo {
  address: string,
  mnemonics: string,
  privateKey: string,
  publicKey: string
}

type BitcoinType = {
  multiply(a: number, b: number): Promise<number>;
  // signMessage(): Promise<string>;
  generateMnemonic(length: number): Promise<WalletInfo>;
  mnemonicToWallet(mnemonic: string, path: string): Promise<WalletInfo>;
  // signMessage(msg: string, privateKey: string): Promise<string>;
};

const { Bitcoin } = NativeModules;

export default Bitcoin as BitcoinType;
