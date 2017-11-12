import React, {Component} from 'react';
import { StyleSheet, Text, TextInput, TouchableOpacity, Image, View, KeyboardAvoidingView } from 'react-native';
import {ServerBackend} from '../../../configure';
//import LoginForm from './LoginForm';

Accept = false; 

export default class Login extends Component {

     state = {
      username: '',
      password: ''
   };


   handleUsername = (text) => {
      this.setState({ username: text })
   }
   handlePassword = (text) => {
      this.setState({ password: text })
   }
   login = (email, pass) => {
      fetch('http://' + ServerBackend + '/v1/User/Login', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
        body: JSON.stringify({
          username: email,
          password: pass,
        })
      })
      .then((response) => response.json()) 
      .then((responseJson) => { 
      //alert("Login Success: " + responseJson.Login) 
      //Accept = responseJson.Login;
      Accept = responseJson.Login;
    }) 
      .catch((error) => { console.error(error); });
   }

  render() {
    const { navigate } = this.props.navigation;
    return (
      <KeyboardAvoidingView behavior="padding" style={styles.container}>
        
        <View style={styles.formContainer}>
          <View style={styles.logoContainer}>
            <Image style={styles.logo} source={require('../../images/sleep.jpeg')}></Image>
          </View>
            <Text style={styles.title}> NapIT </Text>
            <View style={styles.container}>
              <TextInput placeholder="username" onChangeText = {this.handleUsername} returnKeyType="next" onSubmitEditing={()=>this.passwordInput.focus()} keyboardType="email-address" autoCapitalize="none" autoCorrect={false} style={styles.input}></TextInput>
              <TextInput placeholder="password" secureTextEntry onChangeText = {this.handlePassword} style={styles.input} ref={(input) => this.passwordInput = input}></TextInput>
              
              <TouchableOpacity style={styles.buttonContainer} onPress = {() => { this.login(this.state.username, this.state.password); screenProps={name:this.state.username}; if(Accept === true){ navigate('SleepLink')} } }  > 

                <Text style={styles.buttonText}> LOGIN </Text>
              </TouchableOpacity>
            </View>
          
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
  },
  input: {
    height: 40,
    backgroundColor: 'rgba(255,255,255,0.7)',
    marginBottom: 20,
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

////<LoginForm> </LoginForm>