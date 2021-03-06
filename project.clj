(defproject kevin "0.1.2"
  :description "It's like Kevin Bacon is right here!"
  :url "http://imdb.com"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/tools.nrepl "0.2.3"]
                 [org.clojure/core.async "0.1.303.0-886421-alpha"]
                 [commons-codec "1.6"]
                 [hiccup "1.0.5"]
                 [enlive "1.1.5"]
                 [environ "0.5.0"]
                 [lib-noir "0.8.4" :exclusions [org.clojure/core.cache ring org.clojure/tools.reader]]
                 [ring-server "0.3.1"]
                 [ring "1.3.0"]
                 [compojure "1.1.8"]]
  :plugins [[lein-ring "0.8.10"]
            [lein-beanstalk "0.2.7"]]
  :aws {:access-key ~(System/getenv "AWS_ACCESS_KEY_ID")
        :secret-key ~(System/getenv "AWS_SECRET_KEY") }
  :ring {:handler kevin.system/handler
         :init kevin.system/init
         :destroy kevin.system/destroy }
  :profiles {:production
             {:ring {:open-browser? false :stacktraces? false :auto-reload? false}
              :dependencies [[com.datomic/datomic-pro "0.9.4815.12"]]}
             :dev {:source-paths ["dev"]
                   :dependencies [[com.datomic/datomic-free "0.9.4815.12"]
                                  [org.clojure/tools.namespace "0.2.4"]
                                  [org.clojure/java.classpath "0.2.2"]
                                  [javax.servlet/servlet-api "2.5"]
                                  [ring-mock "0.1.5"]]}}
  :jvm-opts ["-Xmx4g" "-server"]
  )
