	� G )/accs/remote_bomb_black/deformed_bomb.motx�       �V�o�D?�R��^����� �C�U��EmYq�{�Lc{,ϴ�ժ�6�T������		q��5ޮ�3x3�8�-��(J�����������.1��T퇮IY�G�vV��8*q��Օ���d�0Rb3Yh���p�l��fѩ���FDr r&ʏ���.��!��킈���k3��z���d$F�q����'G��{�;;'�AahhJFP�^X�f����p~ y|q/�����6۾zo�-6@�Fa�L����iv���s����9��
'(���+����9�.�ƿT���߫��Օ"�T�[B?b���tV����(@��������j����|�����~�)�3�I�R$T�9ΐT';v�z",�K�x��D�!��&-�ooo~��_�~�uu�E5��z��mп��T�?U�� �����^|�'�KI4�g(��35����4H�l4�:�m��Di������:v-u����yW��)����qum��YaQ�Da��#Y��K�hh$E]A2i�i�'jWCǿ&��w������6�0�	��a�{ޕ��K�x=%iHV?%�$��JwSB�M����L@����[A~��-��ƫ�N-	c����c8�Cq�(��P�9�wE�U}*���L��ԟi�[�m؉�ڶ!�����_PA�i�.�
���Y��P�0�8](�i�7nx�/�o��=R��'$3�>$������Ŷ1)��������Ɔh[
oW�͕�n{��\?8���u���u�ՒTe��h�M�e%y,�`����;ײcM��LW�����4ѨH!���K�*�3�eѶyU�89�X ��_�&�ٱ_Jץ�5�9���4ݑz �T_d�b:�f�J�A�G���m��V�����	C �g�� 3����� g6a/'E�(�kx�|�! 
�5 �2�>99�~����R�x�~�u�;�{~}�8 ��}�sr�P88��#��n�Ѕ��������R�@�W���yp>��?.��5�������^���Q��P���c�%b��=��Ik�i-���K\�k7�q��t��u*��M-�v�:We]4E2'.��"�&]e����[�1�����J�W�!ʗx����F
   o  Lga2/stage/AmpedScript; e Lkotori/data/MixtureData; mix�� kotori.data.MixtureData� /efx/mix/bar2.agi�� j start destroy remote_bomb_explode 	damagehit broken
 H 0 3 setEdge (Z)V
 f q r 
setVisible
 f t r anime (F)V
 d v w setShadowState
 d y 3 lockItemScript
 f { r enableCollision
 f } r (FF)V
 d v =L�� 	playSound
 d � 3 generate /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � �D &/accs/remote_bomb_black/pole_light.scm            ?�  ?�  ?�              D '/accs/remote_bomb_black/frame_light.scm            ?�  ?�  ?�              D '/accs/remote_bomb_black/frame_light.scm            ?�  ?�  ?�              D '/accs/remote_bomb_black/frame_light.scm            ?�  ?�  ?�               A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � �D /efx/flash/glow.scm�            ?�  ?�  ?�               	red_loop4 Lkotori/gfx/KGLScriptModel; � startScript (Ljava/lang/String;)Z
 � � � fade_red vibe (FFFFI)V
 d � �;�`BCH   setJudgmentKind
 f � 3@@  <� 
setMixture (Lkotori/data/MixtureData;)V
 d � � mixtureMotion (IFFF)V
 d � �@�   Lkotori/geom/KMath; � D2R (F)F
 � � � sin
 H � � move (FFF)V
 f � �;#�
 clearJudgmentKind
 f � 1D /efx/explode/exp_sphere1.scm�            ?�  ?�  ?�                start10 finishAllConnections
 d � 1�� ga2.stage.atk.DiskAttack�� � hit_h0(  d��� K     Lga2/stage/atk/AttackParam; � hitscp	 � � 
 hit_bg hitefx	 � �  hit_h0 Lga2/stage/atk/DiskAttack; � r	 � � ?�ff=��� h	 � �  
downattack	 � �  attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-            -             kill
 f � 1 
stopScript
 f � 1=#�
 f h g j  - 1  i� �  k 1 &�*� pW*� sW*� uW*� xW*d� pW*� uW*� zW*� |W*� ~W*�� �W* Ҷ �W*�� �L*�� �M*�� �N*�� �:*��� �W*2� pW+� 
+�� �W,� 
,�� �W-� 
-�� �W� �� �W* �� pW*�� �W*��d� �W* Զ �W*2� pW*� �W*� |W*��� �W*� � �W*�� �W� '*��h�� �� �j� �W*2� pW�Y`�W��ܱ  l 1  �*� �W*� |W*� pW*!� �W*��� �W*2� pW*� uW*� �W*2� pW�L	>=� dd� +�� �+� �� +�� �+(� �+���jb� �+���jb� �+� �*�+� �W+� �*�+� �W* ȶ pW�W���*� �W*� �W�  m 1  *� W�  n 1  ?*��\d� �W*� �W� '*��h�� �� �j� �W*2� pW�Y`�W��ܱ  o 1  *� W� BJ��            >#�
=���>#�
� � 	collision�1            >�33>8Q�>�33