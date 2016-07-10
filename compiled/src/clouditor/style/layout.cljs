
(ns clouditor.style.layout)

(def row
 {:align-items "stretch",
  :justify-content "flex-start",
  :display "flex"})

(def column
 {:align-items "stretch",
  :justify-content "flex-start",
  :display "flex",
  :flex-direction "column"})

(def fullscreen
 {:top 0, :width "100%", :position "absolute", :height "100%", :left 0})

(def sidebar {:width "25%"})

(def flex {:flex 1})

(def toolbar
 {:align-items "center",
  :padding "8px",
  :justify-content "flex-end",
  :display "flex",
  :flex-direction "row"})

(def container {:padding "16px"})
