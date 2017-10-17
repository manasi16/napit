var mongodb=require('mongodb');  // includes mongoDB 
var mongodbClient=mongodb.MongoClient; //initialises the mongoDB client

var mongodbURI='mongodb://192.168.10.170:27017/NapIt'
var collection;


//class Database{

function setupCollection(err,db) 
{  
  if(err) { throw err;}
  
  //console.log('Connecting to the Mongodb server');
  collection=db.collection("NapIt"); //name of the collection in the database

};

function storeJson(input)
{

	collection.insert(input, {w:1}, function(err, result)  {
		if(err)
		{
			console.log("Insert failed with error ",err.message);
			console.log('Last insert ID:', result.insertId);
		}
	})
};


mongodbClient.connect(mongodbURI, setupCollection);

function getFirstEntry( queryname )
{
	var DocOut;
	collection.findOne(queryname, function(err,document)
	{
		DocOut = document;
	});

	return DocOut;
}

function getAllEntries( queryname )
{
	var DocArrayOut = {};
	collection.find(queryname).forEach(function(item))
	{
		DocArrayOut.push(item);
	}
	// figure out what to return 
	return DocArrayOut;
}

//}
