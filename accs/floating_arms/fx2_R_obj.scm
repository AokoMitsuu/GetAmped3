	� G %/accs/floating_arms/floating_arms.mot k�       �X_kG��O��]��t��T�uL���J��J��t{�l����)����}�)}�K�2~���;��$%%�/��I7������i��k2�$m��Q $1�H� �4�k�j�CS*z�$y��ty�2�%�3�8����k���H�����o��搝����k�$� �Xb3�m��%e�&hf}N��ꀎ�Cs������/�/���yjA�c,���? ]l)q�_^�Y
�T�I�
���k�K��ǘ4f�C��nA�Ղ��j�J�� �k�/��J"(��֮�Dǔ�����3�&X^M����.'� -���z��a��:�|H�(��Ǆ�^d�Iy��Dp{�Gz�hA�\z&"�����x���A��	lW/���I�x=�PE/��-j�1mOm�v`� �8�r�AS���2N��p�M�	a�2�u����8�N�i�	`���}�>�1�=���
�ꬂʐ��t��Z��;O8z����Q�Mn���u>�H�>FP�C�à��Z�i���� ���I{Y�[U���;I	 aW1�ԛQ����ῳ�:�h�TPN�),�x��;��0I|��@SϚ�[C��g��������O�?��}{>>�e|����ߍϯ�W*?&��5�K_�^ ���
1�i���SK�M��98�t#Bn)�Φ�VP��~�2��荋�g���Y\�FPVکET�ɀ6<-���yV_�=&(��]A���k��HdV pnεƦ����ɁYe�B���ζ�@[�qn��d�tU����ǀ|��^�
D)�ՠ���V*�`�nV���U_��b:�J���y�脄��y�4�����F�M�e�O�Ą-$ˬ��?�o�֟锳k��qTd�����	[l�1Ez,�J~�JN�o�,�3ݻ�戡V��V��HXF�Gz��^�mz�n�����nCϊl��4��P۪�ǜ	0?Z��{��l��_�ٍ��s�o���nx[��S�F�x���}��5Żr[����~Ż�w�x߻x�����mq�'邼;�r�ṗ֛J}H0S�Ϳ��ܱ���%` S��D}�|�1��Ga�!#*ܔl�`����!@���T/�6gG�ES�d��-&9�"��oF3��-�̈́�jj��7�󏞲���r�"*ZD�Э�������ˋ&U������`LV;��f,_��m�Ux�%��p��̦wƧT4^af.&�*L��M�Nǎ��W��@�00��Գ��_��pS��l����K���¡+ef�ׄqQ����	�C���gV㳝�;�ϛ���[�[�ww6 �������è��1Md��ۛ�ߔ �w�   �  Lga2/stage/AmpedScript; e Lga2/stage/atk/ShootAttack; atk� d�B�D /efx/laser/l_bbss_gre4.scm�            ?�  ?�  ?�                 d�    d  �� d�       ,�>�         start start_attack start_attack2 start_attack3 broken 
setVisible (Z)V
 f o p setShadowState
 d r 3 lockItemScript (ZZ)V
 f t u
 H 0 3 vibe (FFFFI)V
 d x y<#�
 finishAllConnections
 d | 1 clearGenerate
 d ~ 1 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � �D /accs/floating_arms/fx1_R.scm����    >��
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
 f � �-�L��@   ?�  ->L��@   @@  -    @   @�   lockTimeCount
 f � p Lkotori/scp/KotoriScript; � realtime
 � � 2
 H 0 1 existAttack
 f � � getGenerate (I)Lkotori/gfx/KGLScriptModel;
 d � �
 � � 1 	crack_endD /accs/floating_arms/arms.scm�            ?�  ?�  ?�                getParentPlayerModel ()Lga2/stage/PlayerModel;
 f � � pos
 � � � back2_R putParam '(Ljava/lang/String;Ljava/lang/Object;)V
 � � � bc_attacker setFlagsToModifier (ILjava/lang/String;I)V
 f � � floating_arms    f h g - 1  i� �  j 1
 O*� qW*� sW*� vW*d� wW*{\d� zW*� }W*� W*��� �L*+� �W�M,�� �W,�� �,�� �W**,�� �� �W,�� �W**,�� �� �W*d� wW*� vW*d� wW+� 
+�� �W*d� wW*� �� �*� �N*� �::� e*� �6� V� �:*� �W*� �:� 9� �� �� �:� �� �� �W� �8		��� :� :*-� �W*-� �W*k-� �W*� wW*� �W*� �W�  k 1 *� �L+� *+� �M*+� �N*� wW*� �:-� +� %-� �*� ̸ Ѷ �� �W*-� � �W� *�� � �W*X� wW*� �� �*� �::,� d*,� �6� V� �:*� �W*� �:� 9� �� �� �:		� �� �� �W	� �8

��� :� :*+,� �W*+� �W*l+� �W*� wW*� �W*� �W�  l 1 *� �L+� *+� �M*+� �N*� wW*� �:-� +� %-� �*� ̸ Ѷ �� �W*-� � �W� *�� � �W*X� wW*� �� �*� �::,� d*,� �6� V� �:*� �W*� �:� 9� �� �� �:		� �� �� �W	� �8

��� :� :*+,� �W*+� �W*m+� �W*� wW*� �W*� �W�  m 1  h*� �L+� *+� �M*+� �N*� wW*� �:-� +� %-� �*� ̸ Ѷ �� �W*-� � �W� *�� � �W*X� wW*� W�  n 1  �*� vW*� �W*� wW*� ��`<� *� �� � *� �W*� ���*� �M*� �N*� �:,� ,� �W-� 
-� �W� � �W�:*� �:� &� �W*� �:� �� �W*�� wW*1��� �W*�� wW*� �W*� �W� �@�7    ?S(6���p?�L^?>t�?�[�  