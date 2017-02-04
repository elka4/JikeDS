var o = {
	a: 1,
	b: 2,
	c: 'str',
	d: false
};

for(var key in o) {
	console.log('key: ', key, 'value: ', o[key]);
}