	� G %/accs/floating_arms/floating_arms.mot ��       �W[oG~�S#�u��];q
r�BH" �������뱳�^��q����I)T
�BPA!�T
J)�/]�$O�=3�7����CU����=s��3g>�7��.��Y�%jK�f�g4G����]�Y���lb;�TV�*uo"W�mnE�4���ĖD�̱Ͷ�`��8CFB:��y[/#�9DH�۽������%�pq:ω3�j�>J��h����&S N��>�jMu]!�(�s���rA7����W~����a� �-��آf`��v�C��U��=c����񙡔�j�+W[%�U-�����m����H�f��"J��dS�(�,�E���5�����_^�X�|��=h�9�{k��^tw;���,�]'�q��;�@%BV�0�I�'�"p�l��X�d�.��)�Ȃ��9ׇCTC:�B ."��.���:�-�FͰ˸؀7��	N��H�1�x\ߜnA��]�������wr�;�y!�2�!#��B�V�J�=�-X��ER4i�Z��/�W_�W��.�������o�s�"$)!� �ʳ���J�R�*C�t�Od6����S��[/	f�}��w��n��߻�/]�����)'K$2�z��Uɬ���@R��-�����bɣ��>^13 *�`����X�}��p����=Y��z��I��/<j�|���k����t�u�n���ث��g��c*��ذea��C�+DHw���4�6� ��p �@��ځ�>%Z�B}J���u�;��%67�༡��upƄ-
�<��	���	�x��T���l3�_3�?�(�|�Ln*q����8+�&�B���˲�۟��/��K^��=^���#���m�~���= �^�B#k_��{�)�G������{ �ǉr4(u���A��r����O,Ϋ���I���潩[BNdĴܟPFNU�D:"Ԇ���36�ދ2y{��e�Ooo=y��CVwk�b��*�qv�cѝ���}�>��S�ۢgղv����u�۲�t ��m��l��o�o �����l+{�/&PP�8�҃ʜ<M7֛���y��ȳ�����G�`�>{z�L�Y�2݂ޓIw3��LűP$�O�	 �2k���X��B,�3�R�#��8ɲ|�y�˳7Y<b��{��q2������:��Q�e����X)d�ΰ'���Xαs%<�Zk�ګ78t:;��}a�Ïr&0�9��O����\��#?Uª�����K��IɈP�?�f��ֹ��G�1:C��aBTm��J7`�$(R�ؿ���9-�([��+�Z�`xÆQ����P&'��W���y�j*���RZVM�A�>�9�W��g
ފ%�
�`�x:v4l�>�=fTB��2\(LgƏ���E W�6���� 35��^�%�̮v���ɎVB�"��h�ОA�i��&V�|�`zefg�.&�y���1L�b��ν���������zC{��W�ҝ�#D�H�A��e�>�i�R2�"�Ȃ��;��Z����I�DN�("�A (/�.u�4�>��P��� $��t*��/9�R9�V�N�&�JZ�#;p��qk�� = �ÆZu6x�W`C�'�NNGƇ'�F�#�#�;WP������v(���"���UDu�h�6�a�A��	�N�Ļ   �  Lga2/stage/AmpedScript; e start floating_arms_explode_x_execute 
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
 d � �D "/accs/floating_arms/shootR_sx1.scm�            ?�  ?�  ?�               start2 isServer ()Z
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
 � � � back2_R putParam '(Ljava/lang/String;Ljava/lang/Object;)V
 � � � bc_attacker setFlagsToModifier (ILjava/lang/String;I)V
 f � � floating_arms    efx_endD /accs/floating_arms/arms.scm�            ?�  ?�  ?�                f  g 1  }*� rW*� tW*� wW*d� xW*� wW*� zW*� |W*� ~W**��� �� �W* ȶ xW*� ��  L*� �M,� 
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
+�� �W�M*� �N-� #,� �W*,� �:� �-� �W*�� xW*1��� �W*�� xW*� �W*� �W� Q׬    ?S(6���p?�L^?>t�?�[�  