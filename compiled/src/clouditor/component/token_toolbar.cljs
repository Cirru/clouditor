
(ns clouditor.component.token-toolbar
  (:require [respo.alias :refer [create-comp div]]
            [respo.component.text :refer [comp-text]]
            [respo.component.space :refer [comp-space]]
            [clouditor.style.widget :as widget]
            [clouditor.style.layout :as layout]))

(defn on-rm [e dispatch! mutate!] (dispatch! :tree/rm nil))

(defn on-after [e dispatch! mutate!] (dispatch! :tree/after nil))

(defn on-before [e dispatch! mutate!] (dispatch! :tree/before nil))

(defn on-fold [e dispatch! mutate!] (dispatch! :tree/fold nil))

(defn render []
  (fn [state mutate!]
    (div
      {:style (merge layout/row widget/float-toolbar)}
      (div
        {:style widget/tool-button, :event {:click on-rm}}
        (comp-text "rm" nil))
      (comp-space 8 nil)
      (div
        {:style widget/tool-button, :event {:click on-after}}
        (comp-text "after" nil))
      (comp-space 8 nil)
      (div
        {:style widget/tool-button, :event {:click on-before}}
        (comp-text "before" nil))
      (comp-space 8 nil)
      (div
        {:style widget/tool-button, :event {:click on-fold}}
        (comp-text "fold" nil)))))

(def comp-token-toolbar (create-comp :token-toolbar render))
