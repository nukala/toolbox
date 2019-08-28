package org.ravi.rethinkdb;


/**
 * All the drivers were unuasable. <br/>
 * THere is one that looks good, but requires jdk8
 */
public class Example {
	@SuppressWarnings("unchecked")
	public static void main(String... args) {
		// String hostname = "localhost";
		// int port = 29015;
		//
		// RethinkDB r = RethinkDB.connect(hostname, port);
		// // Any use of db set the default db
		// r.db("test").table_create("characters");
		// // A simple Insert
		// r.db("test").table("characters").insert(Arrays.asList(
		// new HashMap() {
		// {
		// put("name", "Worf");
		// put("show", "Star Trek TNG");
		// }
		// },
		// new HashMap() {
		// {
		// put("name", "Data");
		// put("show", "Star Trek TNG");
		// }
		// },
		// new HashMap() {
		// {
		// put("name", "William Adama");
		// put("show", "Battlestar Galactica");
		// }
		// },
		// new HashMap() {
		// {
		// put("name", "Homer Simpson");
		// put("show", "The Simpsons");
		// }
		// }
		// ));
		//
		// // Then a Simple Query
		// r.table("tv_shows")
		// .filter(new HashMap() {
		// {
		// put("name", "Star Trek TNG");
		// }
		// });
		// // Returns Array(HashMap( ("name","Worf") , ("show","Star Trek TNG") ),HashMap( ("name","Data") , ("show","Star Trek TNG") ))
	}
}
