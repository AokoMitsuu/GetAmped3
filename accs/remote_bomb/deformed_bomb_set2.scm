	� G #/accs/remote_bomb/deformed_bomb.mots�       �VMk�F>���������[��:8�k�M���ۢ�F��4B3v6C��rH�CK���RZ
��K��Q�3�ΌV���ka��z�}�y��c�4d(���]�����ͬ@�QT���K�K8+H��a��f��\���"�"8N��MB6�șt@�q��1.\��<d�%�5�C5PW� ��dyI�H�`�`���/w:����:��a�Y(Z@��a�ak|�������C����.<l�����_6�X�(L�I�]>-��q���vFp��	J&=;�B�bY9C�����j�su�guy��T���vK�#�����S��(��Ga{U]�Z�~����_]���1�p=	S��D��I9!�؍��X�K���D�!��[Z���\�Q���y�mu�U5zV=}�6�/���_��� � }������R��d�L�Ay�n�4H�,4�:�m��Di������:vm�����<�+N���6���quew�3]��(�V�2�P�K��U�u��I�Lk=Q�8���l��s'�����H�o'��-���I(�A�5^7IҾ�K�#I3�ŝ��b]k2�-'��� ?MӖZ�ƫ�N-	c�m���0��P��{�7C�n���O%՝J�3܅:3m{ð;1\�6D����*�b.���PA��?�{��>G�se4)�����%���J�u�o�2����z�����6�唐R�U_=Y[�I�mI�����W)�������m�P?Y�Z-IU�}?�����QV�ǲ�E)
˙s-:ָ	Lu�I�<O s��?;��9�\�GQ�ɒ��"x���j���t]�D�c��L�-�Q��<s��&ca4Pa��4At�l�o�~�aSZ��! ��g�j3��N�� g�a-'E�(�kx�|�! 
���2�<>>�|�w���I��1����-����� ��������6pp�}G"
��;���=11�%�#��r� po��s���|���q����� 7ګ�{i�FV.C�咈���g3$��o���&b�?,qݮ�d�2�i*ә2Rԩ,~4�0�o;u�ʺh�d�\f�E��ʺ!�q��c<�'��J�[��x���.
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
 d � �D  /accs/remote_bomb/pole_light.scm            ?�  ?�  ?�              D !/accs/remote_bomb/frame_light.scm            ?�  ?�  ?�              D !/accs/remote_bomb/frame_light.scm            ?�  ?�  ?�              D !/accs/remote_bomb/frame_light.scm            ?�  ?�  ?�               A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
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
-�� �W� �� �W* �� pW*�� �W*��d� �W* Զ �W*2� pW*� �W*� |W*��� �W*� � �W*�� �W� '*��h�� �� �j� �W*2� pW�Y`�W��ܱ  l 1  �*� �W*� |W*� pW*!� �W*��� �W*2� pW*� uW*� �W*2� pW�L	>=� dd� +�� �+� �� +�� �+(� �+���jb� �+���jb� �+� �*�+� �W+� �*�+� �W* ȶ pW�W���*� �W*� �W�  m 1  *� W�  n 1  ?*��\d� �W*� �W� '*��h�� �� �j� �W*2� pW�Y`�W��ܱ  o 1  *� W� BJ�E            >#�
=���>#�
� � 	collision�1            >�33>8Q�>�33