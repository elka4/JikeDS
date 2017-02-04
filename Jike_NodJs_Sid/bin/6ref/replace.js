var s = 'this is Sid';
console.log(s.replace('s', 'z'));

console.log('replace with /s/g:', s.replace(/s/g, 'z'));

console.log('replace with /s/gi:', s.replace(/s/gi, 'z'));

console.log('replace with /s/g:', s.replace(/s/gi, function(val){
	return val += 'S';
}));