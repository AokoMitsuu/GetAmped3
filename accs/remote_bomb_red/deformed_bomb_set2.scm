	� G '/accs/remote_bomb_red/deformed_bomb.motu�       �VMk�F>���������[�(:8�k�I����2+�v�+i�4v6C��zH�CK���RZ
��K��Q�3�ΌV���ke��z�}�y��c���!�YT�=�%C=ln�9����_^Z^"iN�(�1#Đ����=L�Ep<�F'� 6!� Șt@�q��1.\��2d��5%C5PW� ��lyIIi�a�`����w:��?��:��a�Y8Z@���pM�#_�&p~ y���8���l����M� e�l����`獏$c�x;�$R����p!_�������j�k5�������Z^�QY�vK�c���ΊS��(��a{U]�R�~ҫ��^_��֏II�z��	��K9!�؍��X�K쨏"��ΰ��--�oo��F_ݼ�����}Q=�6�?/������o� }��������Ne�L���m�h��4ko�6�v,��==��Y���][��~"5��5!�>��{8�w\]��y�k
��*pJ�ti��8�r�����e�j�gjS�.6\$��Y=.P�P~�y���?�E�{w��<l����8Ae��%��uJ�u�ɼ�L<�b����4IZj���:Q�E������s�;Cq�B�f(�-YN��d�S�{F�Pw�mo�aǆkۆ(>^S7C�\̥�;*���Q/��'`�t��&�޸�q7���1P�{��NX����2^�>:�Ƹ�bZ��'kk�])�M�6���jI2}� �����'�^�%�ʴ���
������~a�Q1s�E�7��n8���	`a.�a�@��g������8�E��QTa�d8d��n���g�~#]6՘d��o&ɖԁ(��<s���M�P8Pa��5At�l�o�~E(�)-B%��ͳ}�OX��N�� g�a-�y���1��U��(�k ^�`��������/'�����/P��s~���NP&�8���Cm� ����D>��w@�u{b�6K�OGLI�C�����������p���An�W����-8��L���%{u�1�fHZ��Lk1M��?X�]��xe@�D�s�h^���Y���tϭsU�ES$3�2 .�h�U�Q�뼥�>)������l�����V�>
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
 d � �D $/accs/remote_bomb_red/pole_light.scm            ?�  ?�  ?�              D %/accs/remote_bomb_red/frame_light.scm            ?�  ?�  ?�              D %/accs/remote_bomb_red/frame_light.scm            ?�  ?�  ?�              D %/accs/remote_bomb_red/frame_light.scm            ?�  ?�  ?�               A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
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