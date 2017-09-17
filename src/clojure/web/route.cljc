(ns clojure.web.route)

(defprotocol Route
  (url-for [this] [this args]))

(defmulti routes-for identity)
(defmulti handle-route :route)
