// 'use strict';
var gA = 'gA';

function testGlobal(){
	gB = 'gB';
	console.log('gA: ', gA);
}


testGlobal();
console.log('gB: ', gB);