import * as React from 'react';

import { StyleSheet, View, Text } from 'react-native';
import Bitcoin from 'react-native-bitcoin';


const msg = "this is my simple message"
const PATH = "m/84'/0'/0'/0/0"
const mnemonics = 'machine stereo slide remember hover later stove render minor goat tissue buddy'
export default function App() {
  const [result, setResult] = React.useState<number | undefined>();

  React.useEffect(() => {
    Bitcoin.multiply(3, 7).then(setResult);
    Bitcoin.generateMnemonic(16).then(console.log);
    Bitcoin.mnemonicToWallet(mnemonics, PATH);
    // Bitcoin.signMessage(msg, "KzyVb89S5G5XbFoMxfwfk6hq77gNPWaHwBroZL1oUmbk6CcKeQix")
  }, []);

  return (
    <View style={styles.container}>
      <Text>Result: {result}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
