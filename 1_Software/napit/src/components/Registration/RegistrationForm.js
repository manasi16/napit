import React, {Component} from 'react';
import { StyleSheet, View, TextInput, TouchableOpacity, Text } from 'react-native';

export default class RegistrationForm extends Component {
  render() {
    return (
      <View style={styles.container}>
        <TextInput placeholder="username" returnKeyType="next" onSubmitEditing={()=>this.passwordInput.focus()} keyboardType="email-address" autoCapitalize="none" autoCorrect={false} style={styles.input}> </TextInput>
        <TextInput placeholder="password" secureTextEntry style={styles.input} ref={(input) => this.passwordInput = input}> </TextInput>
        
        <TouchableOpacity style={styles.buttonContainer}> 
          <Text style={styles.buttonText}> Create Account </Text>
        </TouchableOpacity>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    padding:10

  },
  input: {
    height: 40,
    backgroundColor: 'rgba(255,255,255,0.7)',
    marginBottom: 20,
    color: '#FFF',
    paddingHorizontal: 10,
    width: 200
  },
  buttonContainer: {
    backgroundColor: '#2980b9',
    paddingVertical: 15
  },
  buttonText:{
    textAlign: 'center',
    color: '#FFFFFF',
    fontWeight: '700'
  }
});