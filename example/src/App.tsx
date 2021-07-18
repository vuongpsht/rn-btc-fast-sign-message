import * as React from 'react';

import { StyleSheet, View, Text } from 'react-native';
import Bitcoin from 'react-native-bitcoin';

export default function App() {
  const [result, setResult] = React.useState<number | undefined>();

  React.useEffect(() => {
    console.log({ Bitcoin });
    Bitcoin.multiply(3, 7).then(setResult);
    Bitcoin.generateMnemonic(16).then(console.log);
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
