	� G ,/accs/remote_bomb_platinum/deformed_bomb.mot��       �VMo�D>o���\���g���i�4��$T�V^{�;Y�cٳ�V�J�撪
 $.@���*�ȯq��?��{��M�T�J�yf���y���>EI�Km��r굑��(8�3�Rgqaq�)ɨ�%�dX<�i��,�F$���Itlģ]����
��v`�¹췇����M8"Ÿ/�����Oj1	l��|~���V�ޝ�[���FQߕu�u�[^fi^�l�A� d��>����Ck8ҝ�e�o����"��*�
�:`��n,r�>D��fl95]�~����߮�����YN�t�#;&8� Ҍ���A&�cT�?+��ß��?���Ņ��s�`6k!Np���0�Apk+h#�f=Twj5�S�
.��_������������}�cH�zQ��
/�16�5�0*��a�]`/ 'P2����uB����~u�ŷ�����Qqvf��������w�ڀ>��o�u�;�"�c3(����"p�$�����jhF#���{p@zI�X�Q���M��
��(As\Z���D�u��s=C1���"q�ي<���$BM.��~,�O�ꕻ�3��)=?i��a��s��b�z޻ŀ�6�/���T12���w�vDN��*g(hF�����q�p�I/��Ri�Q�l9���9%a���5%b���=%b�""hUF%ؚh`AgH�8�<c��c�N�95���8��z�B�ŐA�"�U'%04�lv�[�WQ�c��k����P�C�g\��{>C+k�40���v������:�G�(l�9e�e���r���V傭�e������P+ZC��#9���
��563E���X�q���y��mZ��Ѳ]��ڤ��ߣA��f��~��l*�yaU�hb4�se������F�6�l�ieȬ����E	������s�$�2���1w��Kb=�6��b��Jn⼻N)�X�[�ݫcD1������W��p5�X�0$�\ن#u0��ԕ���2 Fw��f(ϡ�.˕#Q�\{	�"s�����o9Q]vGb�|�9�&�NYv�f�!��Y���]��������s�Zm~폶��&��k�{��kV��r��3����+Ёk�e�8�
��D���-�{i�~IU�U�>_���5R�ܖ1��ZG�h眒�le�-3���){U�bD��� /�P5UVU��U6R��[bL���ʭ�tQ2����s�
   t  Lga2/stage/AmpedScript; e Lkotori/data/MixtureData; mix�� kotori.data.MixtureData� /efx/mix/bar2.agi�� Lkotori/gfx/KGLScriptModel; mix_m2 j start destroy remote_bomb_explode 	damagehit broken
 H 0 3 	finishKGL (Lkotori/gfx/KGLScriptModel;)V
 f s t setEdge (Z)V
 f v w 
setVisible
 f y w anime (F)V
 d { | setShadowState
 d ~ 3 lockItemScript
 f � w enableCollision
 f � w (FF)V
 d { �=L�� 	playSound
 d � 3 generate /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � �D &/accs/remote_bomb_black/pole_light.scm            ?�  ?�  ?�              D '/accs/remote_bomb_black/frame_light.scm            ?�  ?�  ?�              D '/accs/remote_bomb_black/frame_light.scm            ?�  ?�  ?�              D '/accs/remote_bomb_black/frame_light.scm            ?�  ?�  ?�               A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � �D /efx/flash/glow.scm�            ?�  ?�  ?�               	red_loop4 j startScript (Ljava/lang/String;)Z
 � � � fade_redD 0/accs/remote_bomb_platinum/deformed_bomb_mix.scm�            ?�  ?�  ?�               vibe (FFFFI)V
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
 f h g k j l  - 1  i� �  m 1 5�*� rW*�� uW*� xW*� zW*� }W*d� rW*� zW*� W*� �W*� �W*�� �W* Ҷ �W*�� �L*�� �M*�� �N*�� �:*��� �W*2� rW+� 
+�� �W,� 
,�� �W-� 
-�� �W� �� �W* �� rW*�� �W*�� ��*��d� �W* Զ �W*2� rW*� �W*� �W*��� �W*� � �W*�� �W� '*��h�� �� �j� �W*2� rW�Y`�W��ܱ  n 1  �*� �W*� �W*� rW*!� �W*��� �W*2� rW*� zW*� �W*2� rW�L	>=� dd� +�� �+� �� +õ �+(� �+���jb� �+���jb� �+� �*�+� �W+� �*�+� �W* ȶ rW�W���*� �W*� �W�  o 1  *� W�  p 1  ?*��\d� �W*� �W� '*��h�� �� �j� �W*2� rW�Y`�W��ܱ  q 1  *� W� $_�            >#�
=���>#�
� � 	collision�1            >�33>8Q�>�33