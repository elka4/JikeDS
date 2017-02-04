var redis = require('redis');

var client = redis.createClient(6379, 'localhost');

Object.prototype.toString = function(){
  return JSON.stringify(this);
};

client.set('hello', {a: 1, b: 2});

client.get('hello', function(err, v){
  console.log('redis get hello err, v:', err, v, typeof v);
});