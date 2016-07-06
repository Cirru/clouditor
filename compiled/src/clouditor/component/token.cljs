
(ns clouditor.component.token
  (:require [respo.alias :refer [create-comp div]]
            [clouditor.style.layout :as layout]))

(defn render [] (fn [state mutate!] (div {})))

(def comp-token (create-comp :token render))
