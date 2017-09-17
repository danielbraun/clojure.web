(ns clojure.web.route.bidi
  (:require [clojure.web.route :as route]
            [bidi.bidi :as bidi]))

(extend-protocol route/Route
  #?(:clj clojure.lang.Keyword
     :cljs cljs.core.Keyword)
  (url-for
    ([this] (route/url-for this {}))
    ([this args] (bidi/unmatch-pair (route/routes-for :root)
                                    {:handler this :params args}))))
