import * as React from 'react';

import { StyleSheet, View } from 'react-native';
import Bitcoin from 'react-native-bitcoin';

const mnemonics = 'fork such laundry rubber pair park fatigue brother tobacco equal argue mimic'
//xprv9s21ZrQH143K2oaPmgaGyFpZEUwEnWEXhZV2NJQKnqCMmo4GZQwotTChpkPsFN7y8bGLiLJ3mowAJfhccLA8zMNNZLj4RHEHzKTZ5iLFrpW
export default function App() {

  React.useEffect(() => {
    Bitcoin.generateMnemonic(16).then(console.log);
    Bitcoin.mnemonicToWallet(mnemonics).then(console.log);
  }, []);

  return (
    <View style={styles.container}></View>
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
