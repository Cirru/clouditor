
(ns clouditor.style.widget
  (:require [hsl.core :refer [hsl]]))

(def button
 {:line-height 2,
  :color (hsl 0 0 100),
  :background-color (hsl 200 70 70),
  :padding "0 8px"})

(def expression
 {:border-style "solid",
  :border-width "1px",
  :padding "4px",
  :border-color (hsl 0 0 90),
  :display "inline-block",
  :position "relative"})

(def float-toolbar
 {:top "100%",
  :background-color (hsl 200 80 50 0.2),
  :padding "8px",
  :position "absolute",
  :left 0})

(def tool-button
 {:line-height "2",
  :color (hsl 0 0 100),
  :font-size "12px",
  :background-color (hsl 200 80 70),
  :padding "0 4px",
  :font-family "Helvetica"})
