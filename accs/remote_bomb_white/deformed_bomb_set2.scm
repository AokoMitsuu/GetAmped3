	� G )/accs/remote_bomb_white/deformed_bomb.moty�       �V�n�D>;R��8Ob��! �C6	Q6�EIXqy��Lgl���$�ZE�L.���B $$�$�4ެx��=�����h4cwu}U���U��62��T텮IY�C�fV��(*q����%��d�0Rb3Yh����"�"q2��N��l 6"�� g2 a��+l��"�9�CvZ�mPQ3<TueJ��//)�l�}~���N����;[�`AahhJ�@�nX�f����p~ y|q/�����6۾zo��)6@�Fa�L����i�좉�L=�3�cNP2�	7�+���3T]�Z�~�F?VW�WW��KEH�n��}�v��Yy�Z�� �}/t������ zq�����R�!�Π'aJ�0�8CҜ0�؍鱲�.eG�0&�u�t���,�����}y�����j��z��m�?��.�.���|��?�\J��C�,�����v��)@�f���1l�n'B�H�GG�4�uױk��ݏ����TMq���q�䁫+�;��F�J��:]�u;�}̐U�uȤQ���\�B�\d����2�:X�Á�6�w��m8x_: ���|JҐ��^J�%��JwRB�u����,@����;A~��-���YR���1�m�� �7��3�M �Ļ"iU�J�;U�g��3m{ð;1\�6	9�n�]Ε�;�*���Ӹ����cPq�t�^�7ax</�o��=R��'43�> ����Gb��,!�z��&ږ�ەns���^�8��B�7��C�d�k�$T���Zb�cDYI�.�(,gε�X��0�'��<!XX�hX�P��١E��ײh�<�*L�E,��/U��د�������f�nI;�E��#�1l2FU��m�7�Wf0��M�@¬^y��)iT<;}[8c�{9)JD)\c�s�PX��(������G{�;��P
Oد_����~��׎��?�bx�������u�D�[Fp_�'&i���tĴT.$�u8~�y�O���|��T�F{UD/�_#�Q��T���c�%r��<��Ek�i-���J\�k7�p��r��u)����n]��If�e5@^�Ҹ����뼥��>!��G�nI(_�?�v�iF
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
 d � �D &/accs/remote_bomb_white/pole_light.scm            ?�  ?�  ?�              D '/accs/remote_bomb_white/frame_light.scm            ?�  ?�  ?�              D '/accs/remote_bomb_white/frame_light.scm            ?�  ?�  ?�              D '/accs/remote_bomb_white/frame_light.scm            ?�  ?�  ?�               A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
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
-�� �W� �� �W* �� pW*�� �W*��d� �W* Զ �W*2� pW*� �W*� |W*��� �W*� � �W*�� �W� '*��h�� �� �j� �W*2� pW�Y`�W��ܱ  l 1  �*� �W*� |W*� pW*!� �W*��� �W*2� pW*� uW*� �W*2� pW�L	>=� dd� +�� �+� �� +�� �+(� �+���jb� �+���jb� �+� �*�+� �W+� �*�+� �W* ȶ pW�W���*� �W*� �W�  m 1  *� W�  n 1  ?*��\d� �W*� �W� '*��h�� �� �j� �W*2� pW�Y`�W��ܱ  o 1  *� W� BJ�]            >#�
=���>#�
� � 	collision�1            >�33>8Q�>�33