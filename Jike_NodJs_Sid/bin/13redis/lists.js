var client = require('./client');

/*client.rpush('testLists', 'a');
client.rpush('testLists', 'b');
client.rpush('testLists', 'c');
client.rpush('testLists', 1);
client.lpush('testLists', 2);*/

client.lpop('testLists', function(err, v){
  console.log('client.lpop, err, v:', err, v);
});

client.rpop('testLists', function(err, v){
  console.log('client.rpop, err, v:', err, v);
});

client.lrange('testLists', 0, -1, function(err, lists){
  console.log('client.lrange, err, lists:', err, lists);
});