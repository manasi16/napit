import React, {Component} from 'react';
import { StyleSheet, Text, TouchableOpacity, View } from 'react-native';
import { Accelerometer } from 'expo';

export default class Sleep extends Component {
  state = {
    accelerometerData: {},
    SensorReading: Number
  }

   handleSensorReading= (SensorMagValue) => {
      this.setState({ SensorReading: SensorMagValue })
   }
   SendSensor = (SensorMagVal) => {
      fetch('http://192.168.10.160:3000/v1/Sensor', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
        body: JSON.stringify({
          SensorReading: SensorMagVal
        })
      })
      .then((response) => response.json()) 
      // response from the server side
      //.then((response) => { alert("Account created: " }) 
      .catch((error) => { console.error(error); });
   }

  componentDidMount() {
    this._toggle();
  }

  componentWillUnmount() {
    this._unsubscribe();
  }

  _toggle = () => {
    if (this._subscription) {
      this._unsubscribe();
    } else {
      this._subscribe();
    }
  }

  _slow = () => {
    Accelerometer.setUpdateInterval(1000); 
  }

  _fast = () => {
    Accelerometer.setUpdateInterval(16);
  }

  _subscribe = () => {
    this._subscription = Accelerometer.addListener(accelerometerData => {
      this.setState({ accelerometerData });
    });
  }

  _unsubscribe = () => {
    this._subscription && this._subscription.remove();
    this._subscription = null;
  }

  render() {
    let { x, y, z } = this.state.accelerometerData;
    let Magitude = round(getMagitude(x,y,z));
    //this.handleSensorReading(Magitude);
    //this.SendSensor(Magitude;

    return (
      <View style={styles.sensor}>
        <Text>Accelerometer:</Text>
        <Text>x: {round(x)} y: {round(y)} z: {round(z)}  Mag: { Magitude }</Text>
        <View style={styles.buttonContainer}>
          <TouchableOpacity onPress={this._toggle} style={styles.button}>
            <Text>Toggle</Text>
          </TouchableOpacity>

        </View>
      </View>
    );
  }
}

function round(n) {
  if (!n) {
    return 0;
  }

  return Math.floor(n * 1000) / 1000;
}

function averageVal(Val, Num)
{
  /// something in here
}

function getMagitude(X,Y,Z)
{// get the vector magitude
  return Math.sqrt(X^2 + Y^2 + Z^2);
}

const styles = StyleSheet.create({
  container: {
    flex: 1
  },
  buttonContainer: {
    flexDirection: 'row',
    alignItems: 'stretch',
    marginTop: 15,
  },
  button: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#eee',
    padding: 10,
  },
  middleButton: {
    borderLeftWidth: 1,
    borderRightWidth: 1,
    borderColor: '#ccc',
  },
  sensor: {
    marginTop: 15,
    paddingHorizontal: 10,
  },
});






          // <TouchableOpacity onPress={this._slow} style={[styles.button, styles.middleButton]}>
          //   <Text>Slow</Text>
          // </TouchableOpacity>
          // <TouchableOpacity onPress={this._fast} style={styles.button}>
          //   <Text>Fast</Text>
          // </TouchableOpacity>