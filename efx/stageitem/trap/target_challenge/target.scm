	� G 5/efx/stageitem/trap/target_ball/mdl/target_ball_n.mop��       e�Oo�0�/\R����.	�'[Ť���Mc�@a��m��4�-��R�^�pB�p@�_&B|^��['+����{����D�R�<V(!��4I�;���cɄ��z�Lp��;2%n�Y��$��L�������1�dW7�K�Uw;�gT�/��w��Z-�U����c���h>u;W�ԅ�'p�(A}t�����n�д��)���ވ�g.I�	��Q
	¡�^���&?D�[i�H�μ��r#HE�ֵ���)n��*j�� JY��k���:�-�9���PeDS�H�45kKC�����ক�ޒ��5XZ��ٞ��n�����׿�����:�X���Uˢ!5H�2ja��=�a�1�1�9>v����HhNè�N_A5ДѻU��f3M3OK"�z�x�Q�2>��+����@���C��а{Գ��o�/�8�h�/Y[����4u/ޞ��F'��L/�����M�b��-k��ێ͎�	ֆ)�yA�F��Q��לN��s�2��JK>{�����H�ڋv�ߦm�ץXc���z��DƏ �+���C@��C��ݴ�֚�'��kH�m?�A��ǷV���-��.���q�H�$��Uc�7a�Db�2��x�SI3>�k�&����ct   :  Lga2/stage/AmpedScript; e Lkotori/data/MixtureData; p_g�� kotori.data.MixtureData� /efx/mix/bar7.agi � � start 	damagehit destroy guard 
setVisible (Z)V
 f n o
 H 0 3 size (F)V
 f r s?��� setShadowState
 d v 3 anime (FFFZ)V
 d x y@�  A�  A   B    generate /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d  �D */efx/stageitem/trap/item_box/ib_smoke2.ptc�            >�ff>�ff>�ff             setJudgmentKind
 f � 3 Lga2/stage/ItemModel; � JUDGE_DAMAGE_HIT	 � �  	playSound
 d � 3 vibe (FFFFI)V
 d � �=���CH   explode
 d � 1 kill
 f � 1 
setMixture (Lkotori/data/MixtureData;)V
 d � � attack 4(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)V
 f � �-            �  2 � hit_pg    @�     � 2 removeMixture
 d � 1 f h g - 1  i� �  j 1  ]*� pW* � qW*u� tW*� wW*� qW*� pW*{|� zW* �� qW*{}~� zW*�� �W*d� qW*� �� �W�  k 1  *� qW*� �W*��,� �W�  l 1  %*� qW*� �W*� �W*� pW*'� qW*� �W�  m 1  #*� qW*� � �W*��� �W*d� qW*� �W�     >�33    >�(�>�33>�(�� � 	collision�1    >�33    >���>�33>���