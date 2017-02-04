var arr = [22,4,5,111,556,12,100];

var e = arr.every(function(m){
  return m > 10;
});

console.log('every, m>19: ', e);

var s = arr.some(function(m){
  return m > 10;
});

console.log('some, m>10:', s);

var f = arr.filter(function(m){
  return m>10;
});

console.log('filter, m>10:', f);

var m = arr.map(function(m){
  return m + 10;
});

console.log('map, m + 10:', m);