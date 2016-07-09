
(ns clouditor.component.summary
  (:require [hsl.core :refer [hsl]]
            [respo.alias :refer [create-comp div]]
            [clouditor.style.layout :as layout]
            [clouditor.style.widget :as widget]
            [clouditor.style.devtool :as devtool]
            [respo.component.text :refer [comp-text]]))

(defn on-point [index]
  (fn [e dispatch! mutate!] (dispatch! :router/point index)))

(defn on-analysis [e dispatch! mutate!]
  (dispatch! :router/page :analysis))

(defn render [stack pointer]
  (fn [state mutate!]
    (div
      {:style (merge layout/sidebar devtool/dim9)}
      (div
        {:style widget/entry-button, :event {:click on-analysis}}
        (comp-text "Analysis" nil))
      (div
        {}
        (->>
          stack
          (map-indexed
            (fn [index module-name] [index
                                     (div
                                       {:style
                                        (merge
                                          widget/entry
                                          (if
                                            (= index pointer)
                                            {:background-color
                                             (hsl 200 50 80)}
                                            nil)),
                                        :event
                                        {:click (on-point index)}}
                                       (comp-text
                                         module-name
                                         nil))])))))))

(def comp-summary (create-comp :summary render))
