(ns clojure.web.form.formative
  (:require [formative core parse]
            [clojure.web.form :refer [Form]]
            hiccup.compiler))

(defrecord FormativeForm []
  hiccup.compiler/HtmlRenderer
  (render-html [this]
    (-> this formative.core/render-form hiccup.compiler/render-html))
  Form
  (clean-data [this]
    (try (formative.parse/parse-params this (:values this))
         (catch Exception e nil)))
  (errors [this] (:problems this))
  (prepare [this values]
    (let [f (-> this
                map->FormativeForm
                (update :values merge values))]
      (assoc f :problems (formative.parse/with-fallback identity
                           (formative.parse/parse-params f (:values f))
                           nil)))))
