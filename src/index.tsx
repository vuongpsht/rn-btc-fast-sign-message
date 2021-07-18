import { NativeModules } from 'react-native';

type BitcoinType = {
  multiply(a: number, b: number): Promise<number>;
  signMessage(): Promise<string>;
  generateMnemonic(length: number): Promise<string>;
  mnemonicToWallet(mnemonic: string, path: string): Promise<null>;
  // signMessage(msg: string, privateKey: string): Promise<string>;
};

const { Bitcoin } = NativeModules;

export default Bitcoin as BitcoinType;
