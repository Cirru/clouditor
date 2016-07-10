
(ns clouditor.component.tabs
  (:require [hsl.core :refer [hsl]]
            [respo.alias :refer (create-comp div span)]
            [respo.component.text :refer [comp-text]]
            [clouditor.style.layout :as layout]
            [clouditor.style.widget :as widget]))

(defn handle-select [tab on-select]
  (fn [e dispatch! mutate!] (on-select tab)))

(defn render [tabs current-tab on-select]
  (fn [state mutate!]
    (div
      {:style widget/tab-list}
      (->>
        tabs
        (map
          (fn [tab] [tab
                     (div
                       {:style
                        (merge
                          widget/tab
                          (if (= tab current-tab)
                            {:background-color (hsl 200 80 84)}
                            nil)),
                        :event {:click (handle-select tab on-select)}}
                       (comp-text tab nil))]))))))

(def comp-tabs (create-comp :tabs render))
