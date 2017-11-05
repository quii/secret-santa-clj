# secret-santa

Takes a CSV file of email addresses and emails them whoever they need to give a gift to

## to run

`lein run santas.csv`

You will need a `config.clj` that looks roughly like this

````$clojure
{:host "smtp.gmail.com"
 :user "youremail@gmail.com"
 :pass "your password"}
````

And your CSV file should look something like 

````$csv
names,
santa1@gmail.com
santa2@gmail.com
santa3@gmail.com
````