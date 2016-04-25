> You want to open a map app from an app that you’re building. The address, city, state, and ZIP code are provided by the user. What steps are involved in sending that data to a map app?

Get full address by concatinating address, city, state, ZIP code. Example after concatination:
> 2300 Traverwood Dr, Ann Arbor, MI 48105

Use full adress for building intent `URI`
```java
String uri = "geo:0,0?q=full+adrress";
```

Use `uri` to launch create `intent` and `startActivity` with it
```java
Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
startActivity(intent);
```

Additionaly it's good idea to add `try/catch`. Device might not have `Google maps` app installed.