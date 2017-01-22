function debug(myobject) {
	console.log("===== " + myobject + " =====");
	for (var key in myobject) {
		if (myobject.hasOwnProperty(key))  {
		  console.log("key: " + key + " value: " + myobject[key]);
	    }
	} // for
}
