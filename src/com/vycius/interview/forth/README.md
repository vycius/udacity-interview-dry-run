> List and explain the differences between four different options you have for saving data while making an Android app. Pick one, and explain (without code) how you would implement it.

Options to store data:
1. Shared preferences;
2. SQLite database;
3. Files (Internal storage);
4. External storage (e.g using API, FireBase);

Shared preferences is simplest way to save data. It is based on key-value storage. Shared preferences works like a charm on small collection of key values.
At the beginning Android creates SharedPreferences file holding key value pairs. Using simple methods provided by Android API developer can set/remove/update key to some value. Ints, booleans, strings, floats, sets or even objects (after converting them to JSON or ProfoBuff) can be stored easily using Shared preferences Editor. SharedPreferences data persist on device reboot and are removed along with app uninstall.  Shared preferences are great for storing user choices, options, prefilled edit texts. Although storing large datasets or doing difficult operations on stored information is inappropriate and you should consider other storing options.