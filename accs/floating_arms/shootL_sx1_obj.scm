	� G %/accs/floating_arms/floating_arms.mot ��       �W[oG~�S#�u��];q
r�BH"BHUe��cg�����`C#e�R�P#���BD�<�R�_��!O�=3�7����CU����=s��3g>�7��.��Y�%jK�f�g5G����]�Y���mb;�TV�*uo"W�mnE�4���ĖD�̳Ͷ�`��8KFB:��[/#�9DH�ݽ������%�pq:ω�sj�>J��h����&S N��>�jMu]!�(�s���rA7����W~��ݹa� �-��آf`��v�C��U��=c�SZ��顔�j�+W[%�U-�����m�ɢ��H�f��"J��dS�(�,�E���u冿��_Y�X�|���o�>�{���^tw;��,�]��q��;�@%BV�0�I�'�"p�l��X�d
�.��i�Ȃ��9ׇCTC:�B ."��.���:�-�FͰ˸؀7��	N��H�1�Ÿx\߼nA��]��������wr�;�!�2�!#��B�V�J�=�-X��ER4i�Z��7���^�W��.������M򈎂�߹[���P X��G{�R�A)X��T:�'2�\CH��vɭ��ɾeyû�{7|�����/�+K�����%�=T
ުdN�`@W�)W�Yj���t������+���t��`Rw,���|8�{�����Z��}I�ޞ�Z�
(�+W}�k�Z����g�We�)6΀cGU2'9�a�:�>f���W����4�i.m6A~[�@*�H���}J:��;�����)��\wb96Kln6T�yCl����	Z
y{L=d�T��(���fP�fTJQ�`��T�8��&�qFDM�����e��?�{?�_���;�{��ޭ��г�Ӷu���ww��z���}���]�`��ޗ���%��<�p�nV�Jʡn�r>���:�
'I'����n	�9��r.@i9U��P�v��8{/��!��/�~zs��o���[��7�S�밫��T�& ����9��f�=[������'��/ٖ} ��5Ho�ק�k}ɼ�/_b[��})��2ǙE�T��)��^_����G�UlGЇ�<�3���d������L���g*��"a|�`L ��Y[���`����AwM�I����K^�����o��Վ�A�Ǒv��/�r/;-ogx�J ;u�=9$,��r��+��Z�^�Ρ��ѷm�[~�3���Yuvx�%��4����V�Tt��Ђ^�|�HJF�B����5CmβΕU>
��Y:l�j��U�$A�����L���qh�PF�:�8\i�պ�6�b4|4��j09Q��ԗ�#WSa���E�Ҳj�0J���9��Z>S�V,�TH#@��ӱ�a����0�r�BO��Ba�8;~��g/���9��'���$�R.��`v�+�Nv�"14FS����H3��6ɰ�C0�+�0;+tn4�����0�a�cX�t�}���<����GW�ګ�����@��\�!zE��-.�`L㗒!	G������G�a��Hb%r��@qIA�xɮp!��Ơq��M���ե �'S�|�I��I�:uJ5�vPҊY؁�@͌[kx��x6Ԫ[��[�B>819Q8Y�-���OM���\9@mfp0ۏ��ʹC������"�c�@{�����"�O��z8�   �  Lga2/stage/AmpedScript; e start floating_arms_explode_x_execute 
shotCalc_x (Lkotori/geom/Vector3D;)V shot_x (Lkotori/geom/Quaternion;)V broken broken_a broken_x 
setVisible (Z)V
 f p q setShadowState
 d s 3 lockItemScript (ZZ)V
 f u v
 H 0 3 lockTimeCount
 f y q finishAllConnections
 d { 1 clearGenerate
 d } 1 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d  � setGenerate 8(Lkotori/gfx/KGLScriptModel;)Lkotori/gfx/KGLScriptModel;
 d � �D "/accs/floating_arms/shootL_sx1.scm�            ?�  ?�  ?�               start2 isServer ()Z
 f � � getNearestEnemyPos (I)[Lkotori/geom/Vector3D;
 f � � '(Ljava/lang/Object;I)Ljava/lang/Object;
 H * � 
itemScript ((Ljava/lang/String;ILjava/lang/Object;)V
 f � � kill
 f � 1 
stopScript
 f � 1 getArgv ()Ljava/lang/Object;
 f � � getGenerate (I)Lkotori/gfx/KGLScriptModel;
 d � � getPosition ()Lkotori/geom/Position;
 f � �  sub .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 � � � length ()F
 � � �@�  �� kotori.geom.Quaternion?}�5>�e         Lkotori/geom/KMath; � getDir (Lkotori/geom/Vector3D;)F
 � � � R2D (F)F
 � � � Ljava/lang/Math; � round (F)I
 � � �
 f � �C4  C�  �4  	 � R  (FF)F
 � � � min
 � � �B4  A�  ��?�               Lkotori/geom/Quaternion; � clone
 � � �=��� getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � � Lkotori/gfx/KGLScriptModel; � rotate (Lkotori/geom/Quaternion;I)V
 � � ���?}�5>�e         startScript (Ljava/lang/String;)Z
 � � � beam vibe (FFFFI)V
 d � �<#�
 	playSound
 d � 3� ��D /efx/gun/shell_floating_x.scm�        ����@@  @@  @@              � hit_be  d� �    a��       ,�>�         	genoffset 1(Lkotori/geom/Vector3D;Lkotori/geom/Quaternion;)V
 d � � attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-             beam_end
 � � 1D /accs/floating_arms/arms.scm�            ?�  ?�  ?�                getParentPlayerModel ()Lga2/stage/PlayerModel;
 f � � Lkotori/data/KGLGenerationData; � pos (FFF)V
 � � � back2_L putParam '(Ljava/lang/String;Ljava/lang/Object;)V
 � � � bc_attacker setFlagsToModifier (ILjava/lang/String;I)V
 f � � floating_arms    efx_endD /accs/floating_arms/arms.scm�            ?�  ?�  ?�                f  g 1  }*� rW*� tW*� wW*d� xW*� wW*� zW*� |W*� ~W**��� �� �W* ȶ xW*� ��  L*� �M,� 
*,� �L*h+� �W*� xW*� �W*� �W�  h 1  *� �L*� xW*+� W*d� xW*� W�  i j �*� �M,��+��*� �N+-� �W+� �8��� *,� xW*�� W�+� �� �� ��8*� �� �� ��8��� 
�f8��� 
�f8f8��� �f8� ��� 
�b8+� �8+� �+� �8		� �� �� �Zd�8

�� #b
j8b
j�
� ��nj8b8

�f8�:� �:6� /�`�jj8� �W,� �W*
� xW�W
���6� 0�`�jj8� �W,� �W*
� xW�W
���*d� xW� �W,� �W
� �W*� W� *,� xW*�� W�  k l  p*� �M,� 
,Ҷ �W*^� xW*�\�� �W* ϶ �W�N6� '*+� �W*�-� �W*� �W* ȶ xW�W���,� 
,� �W�  m 1  *� W�  n 1  v*� wW*� zW*� xW*� �L+� +� �W�M*� �N-� #,� �W*,� �:� �-� �W*�� xW*1��� �W*�� xW*� �W*� �W�  o 1  x*� wW*� zW*� xW*� �L+� 
+�� �W�M*� �N-� #,� �W*,� �:� �-� �W*�� xW*1��� �W*�� xW*� �W*� �W� Qנ    ?S(6���p?�L^?>t�?�[�  