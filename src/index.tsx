import { NativeModules } from 'react-native';

type BitcoinType = {
  multiply(a: number, b: number): Promise<number>;
};

const { Bitcoin } = NativeModules;

export default Bitcoin as BitcoinType;
