(ns clojure.web.form)

(defprotocol Form
  (clean-data [this])
  (errors [this])
  (validate [this values])
  (get-field [this field-name]))
