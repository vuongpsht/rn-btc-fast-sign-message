import { NativeModules } from 'react-native';

interface WalletInfo {
  address: string,
  mnemonics: string,
  privateKey: string,
  publicKey: string
}

type BitcoinType = {
  // signMessage(): Promise<string>;
  generateMnemonic(length: number): Promise<WalletInfo>;
  mnemonicToWallet(mnemonic: string, path: string): Promise<WalletInfo>;
  // signMessage(msg: string, privateKey: string): Promise<string>;
};

const { Bitcoin } = NativeModules;

export default Bitcoin as BitcoinType;
