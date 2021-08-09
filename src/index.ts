import { NativeModules } from 'react-native';

interface WalletInfo {
  mnemonics: string,
  root32xprv: string
}

type BitcoinType = {
  generateMnemonic(length: number): Promise<WalletInfo>;
  mnemonicToWallet(mnemonic: string): Promise<WalletInfo>;
};

const { Bitcoin } = NativeModules;

export default Bitcoin as BitcoinType;
