import React, {Component} from 'react';
import { StyleSheet, Text, Image, View, KeyboardAvoidingView } from 'react-native';


export default class PersonalDetails extends Component {
  render() {
    return (
      <KeyboardAvoidingView behavior="padding" style={styles.container}>
        <View style={styles.logoContainer}>
          <Text style={styles.title}> NapIT </Text>
        </View>
        <View style={styles.formContainer}>
        
        </View>
      </KeyboardAvoidingView>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#FFF',
    alignItems: 'center',
    justifyContent: 'center',
  },
  logoContainer: {
    alignItems: 'center',
    flexGrow: 1,
    justifyContent: 'center',
  },
  logo: {
    width:100,
    height:100
  },
  title: {
    color: '#FFF',
    marginTop: 10,
    width: 160,
    textAlign: 'center',
    opacity: 0.9
  }
});