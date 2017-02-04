var s = 'str';

switch(s) {
	case "str": 
		console.log('is str');
		break;
	case false:
		console.log('is boolean false');
		break;
	default:
		console.log('default statment');
		break;
}


switch(4) {
	case 2+2:
		console.log('2+4');
		break;
	case 1+3:
		console.log('1+3');
		break;
	default:
		console.log('default statment');
		break;
}