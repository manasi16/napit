import React, {Component} from 'react';
import { StyleSheet, Text, TouchableOpacity, View } from 'react-native';
import { Accelerometer } from 'expo';

 //limit the nmber of sample to send in the array 
  const SendLimit = 20;

export default class Sleep extends Component {
  state = {
    accelerometerData: {},
    SensorReadings: Array,
    SumReading: Number,
    count: Number
  };

  IntervalSum = {
    X: Number,
    Y: Number,
    Z: Number
  };

  // stores a ton of averaged values into 
  DataArray = []; 

   handleSensorReading= (SensorMagValue) => {
      //this.setState({ SensorReading: SensorMagValue })
   }

   SendSensor = (SensorMagVals) => {
      fetch('http://192.168.10.160:3000/v1/Sensor', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
        body: JSON.stringify({
          SensorReadings: SensorMagVals
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
    Accelerometer.setUpdateInterval(100); 
  }

  _fast = () => {
    Accelerometer.setUpdateInterval(16);
  }

  _subscribe = () => {
    this._subscription = Accelerometer.addListener(accelerometerData => {
      let { x, y, z } = accelerometerData;
      if(this.state.count <10)
      {
        this._AddToIntervalSum(x,y,z);
        this.state.count = this.state.count + 1;
      }
      else
      {
        // will return the average value of mag
        this._AddAveragedDataArray(this._GetAverageMag());
        this._ClearIntervalSum();
      }
      

      this.setState({ accelerometerData });
    });
  }

  _unsubscribe = () => {
    this._subscription && this._subscription.remove();
    this._subscription = null;
  }

  _AddAveragedDataArray = (NewValue) => {
    if(this.DataArray.length < SendLimit)
    {
      this.DataArray.push(NewValue);
    }
    else
    {
      this.SendSensor(this.DataArray);
      alert(this.DataArray);
      this._ClearAveragedDataArray();
      this.DataArray.push(NewValue);
    }
  }

  _ClearAveragedDataArray = () => {
    this.DataArray = [];
  }

  _GetAverageMag = () => {
    return getMagitude((this.IntervalSum.X/ this.state.count),(this.IntervalSum.Y/ this.state.count), (this.IntervalSum.Z/ this.state.count) ); 

  }

  _AddToIntervalSum = (X,Y,Z) => {
    this.IntervalSum.X = this.IntervalSum.X + X;
    this.IntervalSum.Y = this.IntervalSum.Y + Y;
    this.IntervalSum.Z = this.IntervalSum.Z + Z;
  }

  _ClearIntervalSum = () => {
    this.IntervalSum.X = 0; 
    this.IntervalSum.Y = 0;
    this.IntervalSum.Z = 0;
    this.state.count = 0;
  }

  render() {
    let { x, y, z } = this.state.accelerometerData;

    return (
      <View style={styles.sensor}>
        <Text>Accelerometer:</Text>
        <Text>x: {round(x)} y: {round(y)} z: {round(z)} </Text>
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

// function averageVal(X, Y, Z, count)
// {
//   /// something in here
//   if(this.state.count >= 10)
//   {
//     this.state.SensorReading = this.state.count;
//     this.state.SumReading = Val;

//     this.state.count = 0;
//     return this.state.SensorReading;
//   }
//   else
//   {
//     this.state.SumReading = this.state.SumReading + Val;
//     this.state.count = this.state.count + 1; 
//     return this.state.SensorReading;
//   }


//}

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