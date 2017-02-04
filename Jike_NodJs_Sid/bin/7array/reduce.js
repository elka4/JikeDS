var arr = [1,2,11,2,11,15];

var r = arr.reduce(function(prev, cur, index, arr){
  console.log('prev, cur, index, arr: ', prev, cur, index, arr);
  return prev + cur;
});

console.log('r: ', r);