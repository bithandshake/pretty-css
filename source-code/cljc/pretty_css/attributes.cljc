
(ns pretty-css.attributes)

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn adaptive-border-radius
  ; @param (keyword) border-radius
  ; @param (number) ratio
  ;
  ; @usage
  ; (adaptive-border-radius :s 0.3)
  ;
  ; @example
  ; (adaptive-border-radius :s 0.3)
  ; =>
  ; "calc(var(--border-radius-s) * 0.3)"
  ;
  ; @example
  ; (adaptive-border-radius nil 0.3)
  ; =>
  ; nil
  ;
  ; @return (string)
  [border-radius ratio]
  ; By using the adaptive border radius solution an element's border can tracks
  ; the curve of its inner or outer border.
  (if border-radius (str "calc(var(--border-radius-"(name border-radius)") * "ratio")")))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn apply-preset
  ; @param (map) presets
  ; @param (map) element-props
  ; {:preset (keyword)(opt)}
  ;
  ; @usage
  ; (apply-preset {:my-preset {...}}
  ;               {:preset :my-preset ...})
  ;
  ; @example
  ; (apply-preset {:my-preset {:hover-color :highlight}}
  ;               {:preset :my-preset})
  ; =>
  ; {:hover-color :highlight
  ;  :preset      :my-preset}
  ;
  ; @return (map)
  [presets {:keys [preset] :as element-props}]
  (if preset (let [preset-props (get presets preset)]
                  (merge preset-props element-props))
             element-props))

(defn apply-color
  ; @param (map) element-attributes
  ; {:style (map)(opt)}
  ; @param (keyword) color-key
  ; @param (keyword) color-data-key
  ; @param (keyword or string) color-value
  ;
  ; @usage
  ; (apply-color {:style {...}} :color :data-color :muted)
  ;
  ; @example
  ; (apply-color {...} :color :data-color :muted)
  ; =>
  ; {:data-color :muted}
  ;
  ; @example
  ; (apply-color {...} :color :data-color "#fff")
  ; =>
  ; {:data-color :var :style {"--color" "fff"}}
  ;
  ; @example
  ; (apply-color {:style {:padding "12px"}} :color :data-color "#fff")
  ; =>
  ; {:data-color :var :style {"--color" "fff" :padding "12px"}}
  ;
  ; @return (map)
  ; {:style (map)}
  [element-attributes color-key color-data-key color-value]
  (cond (keyword? color-value) (-> element-attributes (assoc-in [color-data-key] color-value))
        (string?  color-value) (-> element-attributes (assoc-in [:style (str "--" (name color-key))] color-value)
                                                      (assoc-in [color-data-key] :var))
        :return element-attributes))

(defn apply-dimension
  ; @param (map) element-attributes
  ; @param (keyword) dimension-key
  ; @param (keyword) dimension-data-key
  ; @param (keyword, px or string) dimension-value
  ;
  ; @usage
  ; (apply-dimension {:style {...} :width :data-block-width 42})
  ;
  ; @example
  ; (apply-dimension {...} :width :data-block-width 42)
  ; =>
  ; {:style {:width "42px"}}
  ;
  ; @example
  ; (apply-dimension {...} :width :data-block-width "42%")
  ; =>
  ; {:style {:width "42%"}}
  ;
  ; @example
  ; (apply-dimension {...} :width :data-block-width :s)
  ; =>
  ; {:data-block-width :s}
  ;
  ; @return (map)
  [element-attributes dimension-key dimension-data-key dimension-value]
  (cond (keyword? dimension-value) (assoc    element-attributes dimension-data-key dimension-value)
        (integer? dimension-value) (assoc-in element-attributes [:style dimension-key] (str dimension-value "px"))
        (string?  dimension-value) (assoc-in element-attributes [:style dimension-key]      dimension-value)))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn badge-attributes
  ; @param (map) element-attributes
  ; @param (map) element-props
  ; {:badge-color (keyword)
  ;   :default, :highlight, :invert, :muted, :primary, :secondary, :success, :warning
  ;  :badge-content (string)
  ;  :badge-position (keyword)
  ;   :tl, :tr, :br, :bl}
  ;
  ; @usage
  ; (badge-attributes {...} {...})
  ;
  ; @example
  ; (badge-attributes {...} {:badge-color :primary :badge-content "420" :badge-position :tr})
  ; =>
  ; {:data-badge-color    :primary
  ;  :data-badge-content  "420"
  ;  :data-badge-position :tr}
  ;
  ; @return (map)
  ; {:data-badge-color (keyword)
  ;  :data-badge-content (string)
  ;  :data-badge-position (keyword)}
  [element-attributes {:keys [badge-color badge-content badge-position]}]
  (merge element-attributes {:data-badge-content  badge-content
                             :data-badge-color    badge-color
                             :data-badge-position badge-position}))

(defn marker-attributes
  ; @param (map) element-attributes
  ; @param (map) element-props
  ; {:marker-color (keyword)(opt)
  ;   :default, :highlight, :invert, :muted, :primary, :secondary, :success, :warning
  ;  :marker-position (keyword)(opt)
  ;   :tl, :tr, :br, :bl}
  ;
  ; @usage
  ; (marker-attributes {...} {...})
  ;
  ; @example
  ; (marker-attributes {...} {:marker-color :primary :marker-position :tr})
  ; =>
  ; {:data-marker-color    :primary
  ;  :data-marker-position :tr}
  ;
  ; @return (map)
  ; {:data-marker-color (keyword)
  ;  :data-marker-position (keyword)}
  [element-attributes {:keys [marker-color marker-position]}]
  (merge element-attributes {:data-marker-color    marker-color
                             :data-marker-position marker-position}))

(defn bubble-attributes
  ; @param (map) element-attributes
  ; @param (map) element-props
  ; {:bubble-color (keyword)(opt)
  ;   :default, :highlight, :invert, :muted, :primary, :secondary, :success, :warning
  ;  :bubble-content (string)(opt)
  ;  :bubble-position (keyword)(opt)
  ;   :left, :right}
  ;
  ; @usage
  ; (bubble-attributes {...} {...})
  ;
  ; @example
  ; (bubble-attributes {...} {:bubble-color :primary :bubble-content "Hello bubble!" :bubble-position :left})
  ; =>
  ; {:data-bubble-color    :primary
  ;  :data-bubble-content  "Hello bubble!"
  ;  :data-bubble-position :left}
  ;
  ; @return (map)
  ; {:data-bubble-color (keyword)
  ;  :data-bubble-content (string)
  ;  :data-bubble-position (keyword)}
  [element-attributes {:keys [bubble-color bubble-content bubble-position]}]
  (merge element-attributes {:data-bubble-color    bubble-color
                             :data-bubble-position bubble-content
                             :data-bubble-content  bubble-position}))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn font-attributes
  ; @param (map) element-attributes
  ; @param (map) element-props
  ; {:font-size (keyword)(opt)
  ;   :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
  ;  :font-weight (keyword)(opt)
  ;   :inherit, :extra-light, :light, :normal, :medium, :bold, :extra-bold
  ;  :letter-spacing (keyword)(opt)
  ;   :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl, :auto
  ;  :line-height (keyword)(opt)}
  ;   :inherit, :native, :text-block, :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
  ;
  ; @usage
  ; (font-attributes {...} {...})
  ;
  ; @example
  ; (font-attributes {...} {:font-size :s :font-weight :bold :letter-spacing :s :line-height :text-block})
  ; =>
  ; {:data-font-size   :s
  ;  :data-font-weight :bold
  ;  :data-letter-spacing :s
  ;  :data-line-height :text-block}
  ;
  ; @return (map)
  ; {:data-font-size (keyword)
  ;  :data-font-weight (keyword)
  ;  :data-letter-spacing (keyword)
  ;  :data-line-height (keyword)}
  [element-attributes {:keys [font-size font-weight letter-spacing line-height]}]
  (merge element-attributes {:data-font-size      font-size
                             :data-font-weight    font-weight
                             :data-letter-spacing letter-spacing
                             :data-line-height    line-height}))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn color-attributes
  ; @param (map) element-attributes
  ; @param (map) element-props
  ; {:color (keyword or string)(opt)
  ;   :default, :highlight, :invert, :muted, :primary, :secondary, :success, :warning
  ;  :fill-color (keyword or string)(opt)
  ;   :default, :highlight, :invert, :muted, :primary, :secondary, :success, :warning
  ;  :hover-color (keyword or string)(opt)
  ;   :default, :highlight, :invert, :muted, :primary, :secondary, :success, :warning}
  ;
  ; @usage
  ; (color-attributes {...} {...})
  ;
  ; @example
  ; (color-attributes {...} {:color :default :fill-color :highlight :hover-color :highlight})
  ; =>
  ; {:data-color       :default
  ;  :data-fill-color  :highlight
  ;  :data-hover-color :highlight}
  ;
  ; @return (map)
  ; {}
  [element-attributes {:keys [color fill-color hover-color]}]
  (-> element-attributes (apply-color :color       :data-color       color)
                         (apply-color :fill-color  :data-fill-color  fill-color)
                         (apply-color :hover-color :data-hover-color hover-color)))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn icon-attributes
  ; @param (map) element-attributes
  ; @param (map) element-props
  ; {:icon-color (keyword or string)(opt)
  ;   :default, :highlight, :invert, :muted, :primary, :secondary, :success, :warning
  ;  :icon-family (keyword)(opt)
  ;  :icon-size (keyword)(opt)
  ;   :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl}
  ;
  ; @usage
  ; (icon-attributes {...} {...})
  ;
  ; @example
  ; (icon-attributes {...} {:icon-color :default :icon-family :my-icon-family :icon-size :s})
  ; =>
  ; {:data-icon-color  :default
  ;  :data-icon-family :my-icon-family
  ;  :data-icon-size   :s}
  ;
  ; @return (map)
  ; {:data-icon-family (keyword)
  ;  :data-icon-size (keyword)}
  [element-attributes {:keys [icon-color icon-family icon-size]}]
  (-> (merge element-attributes {:data-icon-family icon-family
                                 :data-icon-size   icon-size})
      (apply-color :icon-color :data-icon-color icon-color)))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn border-radius-attributes
  ; @param (map) element-attributes
  ; @param (map) element-props
  ; {:border-radius (map)(opt)
  ;   {:tl (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
  ;    :tr (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
  ;    :br (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
  ;    :bl (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
  ;    :all (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl}}
  ;
  ; @usage
  ; (border-radius-attributes {...} {:border-radius {...}})
  ;
  ; @example
  ; (border-radius-attributes {...} {:border-radius {:tr :xxl :tl :xs}})
  ; =>
  ; {:data-border-radius-tr :xxl
  ;  :data-border-radius-tl :xs}
  ;
  ; @return (map)
  [element-attributes {:keys [border-radius]}]
  (letfn [(f [result key value]
             (assoc result (keyword (str "data-border-radius-" (name key))) value))]
         (merge element-attributes (reduce-kv f {} border-radius))))

(defn border-attributes
  ; @param (map) element-attributes
  ; @param (map) element-props
  ; {:border-color (keyword or string)(opt)
  ;   :default, :highlight, :invert, :muted, :primary, :secondary, :success, :warning
  ;  :border-radius (map)(opt)
  ;   {:tl (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
  ;    :tr (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
  ;    :br (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
  ;    :bl (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
  ;    :all (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl}}
  ;  :border-position (keyword)(opt)
  ;   :all, :bottom, :top, :left, :right, :horizontal, :vertical
  ;  :border-width (keyword)(opt)}
  ;
  ; @usage
  ; (border-attributes {...} {...})
  ;
  ; @example
  ; (border-attributes {...} {:border-color :default :border-width :s})
  ; =>
  ; {:data-border-color :default
  ;  :data-border-width :s}
  ;
  ; @return (map)
  ; {:data-border-position (keyword)
  ;  :data-border-width (keyword)}
  [element-attributes {:keys [border-color border-position border-width] :as element-props}]
  (-> (merge element-attributes {:data-border-position border-position
                                 :data-border-width    border-width})
      (border-radius-attributes element-props)
      (apply-color :border-color :data-border-color border-color)))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn text-attributes
  ; @param (map) element-attributes
  ; @param (map) element-props
  ; {:text-direction (keyword)(opt)
  ;   :normal, :reversed
  ;  :text-overflow (keyword)(opt)
  ;   :ellipsis, :hidden, :no-wrap, :wrap}
  ;
  ; @usage
  ; (text-attributes {...} {...})
  ;
  ; @example
  ; (text-attributes {...} {:text-direction :reversed :text-overflow :ellipsis})
  ; =>
  ; {:data-text-direction :default
  ;  :data-text-overflow  :ellipsis}
  ;
  ; @return (map)
  ; {:data-text-direction (keyword)
  ;  :data-text-overflow (keyword)}
  [element-attributes {:keys [text-direction text-overflow]}]
  (merge element-attributes {:data-text-direction text-direction
                             :data-text-overflow  text-overflow}))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn default-attributes
  ; @param (map) element-attributes
  ; @param (map) element-props
  ; {:class (keyword or keywords in vector)(opt)
  ;  :disabled? (boolean)(opt)}
  ;
  ; @usage
  ; (default-attributes {...} {...})
  ;
  ; @example
  ; (default-attributes {...} {:class :my-class :disabled? true})
  ; =>
  ; {:class :my-element
  ;  :data-disabled true}
  ;
  ; @example
  ; (default-attributes {...} {:class [:my-class] :disabled? true})
  ; =>
  ; {:class [:my-element]
  ;  :data-disabled true}
  ;
  ; @return (map)
  ; {:class (keyword or keywords in vector)
  ;  :data-disabled (boolean)}
  [element-attributes {:keys [class disabled?] :as element-props}]
  (letfn [(f0 [%] (if % (if (vector? %) % [%]) []))
          (f1 []  (vec  (concat (-> element-attributes :class f0)
                                (-> element-props      :class f0))))]
         (merge element-attributes {:class (f1)
                                    :data-disabled disabled?})))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn tooltip-attributes
  ; @param (map) element-attributes
  ; @param (map) element-props
  ; {:tooltip-content (string)(opt)
  ;  :tooltip-position (keyword)(opt)}
  ;
  ; @usage
  ; (tooltip-attributes {...} {...})
  ;
  ; @example
  ; (tooltip-attributes {...} {:tooltip-content "My tooltip" :tooltip-position :left})
  ; =>
  ; {:data-tooltip-content  "My tooltip"
  ;  :data-tooltip-position :left}
  ;
  ; @return (map)
  ; {:data-tooltip-content (string)
  ;  :data-tooltip-position (keyword)}
  [element-attributes {:keys [tooltip-content tooltip-position]}]
  (merge element-attributes {:data-tooltip-content  tooltip-content
                             :data-tooltip-position tooltip-position}))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn indent-attributes
  ; @param (map) element-attributes
  ; @param (map) element-props
  ; {:indent (map)(opt)
  ;   {:bottom (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
  ;    :left (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
  ;    :right (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
  ;    :top (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
  ;    :horizontal (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
  ;    :vertical (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
  ;    :all (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl}}
  ;
  ; @usage
  ; (indent-attributes {...} {:indent {...}})
  ;
  ; @example
  ; (indent-attributes {...} {:indent {:horizontal :xxl :left :xs}})
  ; =>
  ; {:data-indent-horizontal :xxl
  ;  :data-indent-left       :xs}
  ;
  ; @return (map)
  [element-attributes {:keys [indent]}]
  (letfn [(f [result key value]
             (assoc result (keyword (str "data-indent-" (name key))) value))]
         (merge element-attributes (reduce-kv f {} indent))))

(defn outdent-attributes
  ; @param (map) element-attributes
  ; @param (map) element-props
  ; {:outdent (map)(opt)
  ;   {:bottom (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
  ;    :left (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
  ;    :right (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
  ;    :top (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
  ;    :horizontal (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
  ;    :vertical (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
  ;    :all (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl}}
  ;
  ; @usage
  ; (outdent-attributes {...} {:outdent {...}})
  ;
  ; @example
  ; (outdent-attributes {...} {:outdent {:horizontal :xxl :left :xs}})
  ; =>
  ; {:data-outdent-horizontal :xxl
  ;  :data-outdent-left       :xs}
  ;
  ; @return (map)
  [element-attributes {:keys [outdent]}]
  (letfn [(f [result key value]
             (assoc result (keyword (str "data-outdent-" (name key))) value))]
         (merge element-attributes (reduce-kv f {} outdent))))
