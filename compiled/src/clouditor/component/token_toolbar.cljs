
(ns clouditor.component.token-toolbar
  (:require [respo.alias :refer [create-comp div]]
            [respo.component.text :refer [comp-text]]
            [clouditor.style.widget :as widget]))

(defn render []
  (fn [state mutate!]
    (div
      {:style widget/float-toolbar}
      (div {:style widget/tool-button} (comp-text "rm" nil)))))

(def comp-token-toolbar (create-comp :token-toolbar render))
