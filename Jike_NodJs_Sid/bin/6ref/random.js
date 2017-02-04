console.log('random: ', Math.random());

console.log('random between 5 and 10:', randomBetween(5, 10));

function randomBetween(min, max){
	return min + Math.random()*(max - min);
}