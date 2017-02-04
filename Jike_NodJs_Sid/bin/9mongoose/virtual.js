var mongoose = require('mongoose');

var PersonSchema = new mongoose.Schema({
  fristName: String,
  lastName: String
});

// 虚拟属性的设置
PersonSchema.virtual('fullName').get(function(){
  return this.fristName + ' ' + this.lastName;
});

// 在将对象转换为 json 时，设置同时也要转换虚拟属性的值
PersonSchema.set('toJSON', {getters: true, virtual: true});

var Person = mongoose.model('Person', PersonSchema);

var person = new Person({
  fristName: 'Sid',
  lastName: 'Chen'
});

console.log('user fullName:', person.fullName);

console.log('JSON:', JSON.stringify(person));