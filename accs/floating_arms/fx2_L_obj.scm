	� G %/accs/floating_arms/floating_arms.mot l�       �X�kG��O�6
��T9��T�uL���J��J��t{�l����)����K�ZS�ԗ>���_t��t�pJJJ_��n�7��3�����P��%PG���C�z?#����L����6M��֓������g�K�c�qt��J%��dK����F��� �C?��s�"IT���fp�$%K��M�L����>�'
�f�͏on���������͎��9?��t�ե�qoq!f)(R)��(����-.���.cҘ!X����W�JX��*�ʣ�A�Z\(�����[{��
Sr�^�����`Ax5i4�B4:�, -���z��a��:�|@�(��Ǆ5_d�Iy��Dp{�Gz�hA�\z&"���̈́xm���A��	lW/�ŽI����Z���S���5�6���Y۰eW�
^9� �)�
�(<����-.1�I:!�U�:2�;Ǣ�N C4�4 wð�k҇2&���lꬂʐ��s��Zǅ;�7z��M��
�7��*+Ȃ�"�A�!�a���Z�Q���� ���I�Y�[U���;� aW1��4�����g?Q�)h�TPΝ	,m{��{�� I|��@3Ϛ������������O��?F�}}>:�et����ߌί�W*?&��5�K�6_�>�1Mi���S��M���?�l#Bn)�Φ��P���e U�m���3R��r����S�� �m:0xZxI9=�F�LPH}��2'5�<��)�Ȭ ���k�M��?6�}�ʂ!���/�m	��4=����1Ⱦ�B	I�v�#�y*�|�V���?��R� ;\p�:������ӱ�T�5�8�sD�$Deͫ�9�ژi�٘\� =�$&l!Yf�����	i���r�bu�96���R�c�0a��M2��B��\�K�d�D��V:�29Ѓh�����Z�"ayy��s,��gѦ�6�j*?���Y�=,4l6Զ*�)g�K��������4�0��U[�����S�w��?�n��{7�k�wm�G�{����{߭{��w�.�u�;?z�p�-���G�9yw
�p�7����a�F�����@QZ0�)Np�>z>�� �Ka�!C*ܐl
�e�����C����^�mΎh���|S�7Kr�E8oFS���fLr�������GOY�`�h-��Z����������&U����:gJV;��f*_��6�*��|J��Gfӻ�S*�03�]��'�Q��b�cGw��x U�H��a��-�� ����l���}8���=C�S)3�&�눕Z>���8d-mSxf�?���9|��xZ���:��x���AL$܆�G��+s�L8���� 3�ӕ   �  Lga2/stage/AmpedScript; e Lga2/stage/atk/ShootAttack; atk� d�B�D /efx/laser/l_bbss_gre4.scm�            ?�  ?�  ?�                 d�    d  �� d�       ,�>�         start start_attack start_attack2 start_attack3 broken 
setVisible (Z)V
 f o p setShadowState
 d r 3 lockItemScript (ZZ)V
 f t u
 H 0 3 vibe (FFFFI)V
 d x y<#�
 finishAllConnections
 d | 1 clearGenerate
 d ~ 1 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � �D /accs/floating_arms/fx1_L.scm�?��    >��
?�  ?�  ?�               efx setGenerate 8(Lkotori/gfx/KGLScriptModel;)Lkotori/gfx/KGLScriptModel;
 d � �D /efx/stage/iwa_r/iwa_r.scm�    =L��    ?�  ?�  ?�               Lkotori/data/KGLGenerationData; � scale (F)V
 � � �?��� sy	 � � >#� hpb (FFF)V
 � � �Bp   crack5_2C    Lkotori/gfx/KGLScriptModel; � startScript (Ljava/lang/String;)Z
 � � � burn isServer ()Z
 f � � getObjectArray (I)[Ljava/lang/Object;
 H � � getNearestEnemyID (I)[I
 f � � ([II)I
 H * � ()Lkotori/geom/Vector3D;
 H  � getPosi /(ILkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 f � � getPos
 f � � (FFF)Lkotori/geom/Vector3D;
 H  � 	 � Q 	 � S  sub
 � � � length ()F
 � � �@�   )([Ljava/lang/Object;ILjava/lang/Object;)V
 H + � 
itemScript ((Ljava/lang/String;ILjava/lang/Object;)V
 f � � kill
 f � 1 
stopScript
 f � 1 getArgv ()Ljava/lang/Object;
 f � � '(Ljava/lang/Object;I)Ljava/lang/Object;
 H * � .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 � � � getDir
 f � � Lkotori/geom/KMath; � R2D (F)F
 � � � slopeY2 (F)Lkotori/geom/Vector3D;
 � � �
 � ) � attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �->L��@   @   -�L��@   @�  -    @   @�   lockTimeCount
 f � p Lkotori/scp/KotoriScript; � realtime
 � � 2
 H 0 1 existAttack
 f � � getGenerate (I)Lkotori/gfx/KGLScriptModel;
 d � �
 � � 1 	crack_endD /accs/floating_arms/arms.scm�            ?�  ?�  ?�                getParentPlayerModel ()Lga2/stage/PlayerModel;
 f � � pos
 � � � back2_L putParam '(Ljava/lang/String;Ljava/lang/Object;)V
 � � � bc_attacker setFlagsToModifier (ILjava/lang/String;I)V
 f � � floating_arms    f h g - 1  i� �  j 1
 O*� qW*� sW*� vW*d� wW*{\d� zW*� }W*� W*��� �L*+� �W�M,�� �W,�� �,�� �W**,�� �� �W,�� �W**,�� �� �W*d� wW*� vW*d� wW+� 
+�� �W*d� wW*� �� �*� �N*� �::� e*� �6� V� �:*� �W*� �:� 9� �� �� �:� �� �� �W� �8		��� :� :*-� �W*-� �W*k-� �W*� wW*� �W*� �W�  k 1 *� �L+� *+� �M*+� �N*� wW*� �:-� +� %-� �*� ̸ Ѷ �� �W*-� � �W� *�� � �W*X� wW*� �� �*� �::,� d*,� �6� V� �:*� �W*� �:� 9� �� �� �:		� �� �� �W	� �8

��� :� :*+,� �W*+� �W*l+� �W*� wW*� �W*� �W�  l 1 *� �L+� *+� �M*+� �N*� wW*� �:-� +� %-� �*� ̸ Ѷ �� �W*-� � �W� *�� � �W*X� wW*� �� �*� �::,� d*,� �6� V� �:*� �W*� �:� 9� �� �� �:		� �� �� �W	� �8

��� :� :*+,� �W*+� �W*m+� �W*� wW*� �W*� �W�  m 1  h*� �L+� *+� �M*+� �N*� wW*� �:-� +� %-� �*� ̸ Ѷ �� �W*-� � �W� *�� � �W*X� wW*� W�  n 1  �*� vW*� �W*� wW*� ��`<� *� �� � *� �W*� ���*� �M*� �N*� �:,� ,� �W-� 
-� �W� � �W�:*� �:� &� �W*� �:� �� �W*�� wW*1��� �W*�� wW*� �W*� �W� �@��    ?S(6���p?�L^?>t�?�[�  